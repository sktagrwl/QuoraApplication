package com.example.QuoraApp.services;

import com.example.QuoraApp.adapters.AnswerAdapter;
import com.example.QuoraApp.dto.AnswerRequestDTO;
import com.example.QuoraApp.dto.AnswerResponseDTO;
import com.example.QuoraApp.models.Answer;
import com.example.QuoraApp.repositories.IAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service class responsible for Answer business logic.
 * Follows Single Responsibility Principle - only handles Answer-related operations.
 */
@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {

    private final IAnswerRepository answerRepository;

    @Override
    public Mono<AnswerResponseDTO> createAnswer(AnswerRequestDTO answerRequestDTO) {
        Answer answer = AnswerAdapter.toAnswer(answerRequestDTO);

        return answerRepository.save(answer)
                .map(AnswerAdapter::toAnswerResponseDTO)
                .doOnSuccess(response -> System.out.println("Answer created successfully with ID: " + response.getId()))
                .doOnError(error -> System.out.println("Error creating answer: " + error.getMessage()));
    }

    @Override
    public Mono<AnswerResponseDTO> getAnswerById(String id) {
        return answerRepository.findById(id)
                .map(AnswerAdapter::toAnswerResponseDTO)
                .doOnSuccess(response -> System.out.println("Answer retrieved successfully with ID: " + id))
                .doOnError(error -> System.out.println("Error retrieving answer with ID " + id + ": " + error.getMessage()));
    }

    @Override
    public Flux<AnswerResponseDTO> getAnswersByQuestionId(String questionId) {
        return answerRepository.findByQuestionIdOrderByCreatedAtDesc(questionId)
                .map(AnswerAdapter::toAnswerResponseDTO)
                .doOnComplete(() -> System.out.println("Retrieved all answers for question ID: " + questionId))
                .doOnError(error -> System.out.println("Error retrieving answers for question ID " + questionId + ": " + error.getMessage()));
    }

    @Override
    public Flux<AnswerResponseDTO> getAllAnswers() {
        return answerRepository.findAll()
                .map(AnswerAdapter::toAnswerResponseDTO)
                .doOnComplete(() -> System.out.println("Retrieved all answers"))
                .doOnError(error -> System.out.println("Error retrieving all answers: " + error.getMessage()));
    }

    @Override
    public Mono<AnswerResponseDTO> updateAnswer(String id, AnswerRequestDTO answerRequestDTO) {
        return answerRepository.findById(id)
                .flatMap(existingAnswer -> {
                    existingAnswer.setContent(answerRequestDTO.getContent());
                    existingAnswer.setQuestionId(answerRequestDTO.getQuestionId());
                    return answerRepository.save(existingAnswer);
                })
                .map(AnswerAdapter::toAnswerResponseDTO)
                .doOnSuccess(response -> System.out.println("Answer updated successfully with ID: " + id))
                .doOnError(error -> System.out.println("Error updating answer with ID " + id + ": " + error.getMessage()));
    }

    @Override
    public Mono<Void> deleteAnswer(String id) {
        return answerRepository.findById(id)
                .flatMap(answerRepository::delete)
                .doOnSuccess(response -> System.out.println("Answer deleted successfully with ID: " + id))
                .doOnError(error -> System.out.println("Error deleting answer with ID " + id + ": " + error.getMessage()));
    }

    @Override
    public Mono<Void> deleteAnswersByQuestionId(String questionId) {
        return answerRepository.deleteByQuestionId(questionId)
                .doOnSuccess(response -> System.out.println("All answers deleted successfully for question ID: " + questionId))
                .doOnError(error -> System.out.println("Error deleting answers for question ID " + questionId + ": " + error.getMessage()));
    }

    @Override
    public Mono<Long> countAnswersByQuestionId(String questionId) {
        return answerRepository.countByQuestionId(questionId)
                .doOnSuccess(count -> System.out.println("Counted " + count + " answers for question ID: " + questionId))
                .doOnError(error -> System.out.println("Error counting answers for question ID " + questionId + ": " + error.getMessage()));
    }
}
