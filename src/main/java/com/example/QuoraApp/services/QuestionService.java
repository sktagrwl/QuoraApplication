package com.example.QuoraApp.services;

import com.example.QuoraApp.adapters.QuestionAdapter;
import com.example.QuoraApp.dto.DeleteResponseDTO;
import com.example.QuoraApp.dto.QuestionRequestDTO;
import com.example.QuoraApp.dto.QuestionResponseDTO;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.repositories.IQuestionRepository;
import com.example.QuoraApp.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{

    private final IQuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {

        Question question = Question.builder()
                .title(questionRequestDTO.getTitle())
                .content(questionRequestDTO.getContent())
                .tags(questionRequestDTO.getTags())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(response -> System.out.println("Question has been created" + response))
                .doOnError(error -> System.out.println("Error creating question" + error));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int offset, int page){
        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, PageRequest.of(offset, page))
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error searching question" + error))
                .doOnComplete(() -> System.out.println("Questions Searched Successfully"));
    }

    @Override
    public Mono<PageImpl<QuestionResponseDTO>> getAllQuestion(String cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);

        if(!CursorUtils.isValidCursor(cursor)){
            return questionRepository.findTop10ByOrderByCreatedAt()
                    .take(size)
                    .collectList()
                    .zipWith(this.questionRepository.count())
                    .map(p -> new PageImpl<>(p.getT1().stream().map(QuestionAdapter::toQuestionResponseDTO).toList(), pageable,p.getT2()))
                    .doOnError(error -> System.out.println("Error finding question" + error))
                    .doOnSuccess(result -> System.out.println("All Questions fetched successfully"));
        }
        else {
            LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp,pageable)
                    .collectList()
                    .zipWith(this.questionRepository.count())
                    .map(p -> new PageImpl<>(p.getT1().stream().map(QuestionAdapter::toQuestionResponseDTO).toList(), pageable,p.getT2()))
                    .doOnSuccess(result -> System.out.println("All Questions fetched successfully"))
                    .doOnError(error -> System.out.println("Error finding question" + error));
        }
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(result -> System.out.println("Question fetched successfully by id"))
                .doOnError(error -> System.out.println("Error finding question" + error));
    }

    @Override
    public Mono<DeleteResponseDTO> deleteQuestionById(String id) {
        return questionRepository.deleteById(id)
                .then(Mono.just(new DeleteResponseDTO("Question Deleted Successfully", id, true)))
                .onErrorReturn(new DeleteResponseDTO("Failed to Delete Question" , id, false))
                .doOnSuccess(result -> System.out.println("Question with Id :" + id + " has been deleted"))
                .doOnError(error -> System.out.println("Error deleting question" + error));
    }

    @Override
    public Flux<QuestionResponseDTO> getQuestionByTag(String tag) {
        return questionRepository.findByTagsContaining(tag)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnError(error -> System.out.println("Error finding question by tag" + error))
                .doOnComplete(() -> System.out.println("All Questions has been fetched by tag:" + tag));
    }
}
