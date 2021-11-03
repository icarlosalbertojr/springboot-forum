package com.icarlosalbertojr.forum.services;

import com.icarlosalbertojr.forum.controllers.dto.TopicDetailsResponseDto;
import com.icarlosalbertojr.forum.controllers.dto.TopicRequestDto;
import com.icarlosalbertojr.forum.controllers.dto.TopicResponseDto;
import com.icarlosalbertojr.forum.controllers.dto.TopicUpdateRequestDto;
import com.icarlosalbertojr.forum.exceptions.DataNotFoundException;
import com.icarlosalbertojr.forum.models.Course;
import com.icarlosalbertojr.forum.models.ForumUser;
import com.icarlosalbertojr.forum.models.Topic;
import com.icarlosalbertojr.forum.repositories.CourseRepository;
import com.icarlosalbertojr.forum.repositories.ForumUserRepository;
import com.icarlosalbertojr.forum.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ForumUserRepository forumUserRepository;

    public List<TopicResponseDto> findAllTopics() {
        return topicRepository.findAll()
                .stream()
                .map(TopicResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<TopicResponseDto> findTopicsByName(String courseName) {
        return topicRepository.findByCourse_CourseName(courseName)
                .stream()
                .map(TopicResponseDto::new)
                .collect(Collectors.toList());
    }

    public TopicResponseDto createNewTopic(TopicRequestDto topicRequestDto) {
        Topic topic = new Topic();
        ForumUser forumUser = forumUserRepository.findByEmail(topicRequestDto.getAuthorEmail());
        Course course = courseRepository.findByCourseName(topicRequestDto.getCourseName());
        topic.setAuthor(forumUser);
        topic.setCourse(course);
        topic.setMessage(topicRequestDto.getMessage());
        topic.setTitle(topicRequestDto.getTitle());
        Topic saved = topicRepository.save(topic);
        return new TopicResponseDto(saved);
    }

    public TopicDetailsResponseDto findTopicById(Long id) {
        Topic topic = getTopicById(id);
        return new TopicDetailsResponseDto(topic);
    }

    public TopicResponseDto updateTopic (Long id, TopicUpdateRequestDto topicUpdateRequestDto) {
        Topic topic = getTopicById(id);
        topic.setTitle(topicUpdateRequestDto.getTitle());
        topic.setMessage(topicUpdateRequestDto.getMessage());
        return new TopicResponseDto(topicRepository.save(topic));
    }

    private Topic getTopicById(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        if (!topic.isPresent()) {
            throw new DataNotFoundException("Topic not found!");
        }
        return topic.get();
    }

    public void deleteTopicById(Long id) {
        Topic topic = getTopicById(id);
        topicRepository.delete(topic);
    }
}
