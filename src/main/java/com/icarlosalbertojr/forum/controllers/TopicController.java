package com.icarlosalbertojr.forum.controllers;

import com.icarlosalbertojr.forum.controllers.dto.TopicDetailsResponseDto;
import com.icarlosalbertojr.forum.controllers.dto.TopicRequestDto;
import com.icarlosalbertojr.forum.controllers.dto.TopicResponseDto;
import com.icarlosalbertojr.forum.controllers.dto.TopicUpdateRequestDto;
import com.icarlosalbertojr.forum.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @GetMapping
    @Cacheable(value = "topicList")
    public Page<TopicResponseDto> listTopics(@RequestParam(required = false) String courseName,
                                             @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if (courseName != null) {
            return topicService.findTopicsByName(courseName, pageable);
        } else {
            return topicService.findAllTopics(pageable);
        }
    }

    @PostMapping
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicResponseDto> createNewTopic(@RequestBody @Valid TopicRequestDto topicRequestDto,
                                                           UriComponentsBuilder uriComponentsBuilder) {
        TopicResponseDto topicResponseDto = topicService.createNewTopic(topicRequestDto);
        URI uri = uriComponentsBuilder.path("/topics/{id}")
                .buildAndExpand(topicResponseDto.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(topicResponseDto);
    }

    @GetMapping("/{id}")
    public TopicDetailsResponseDto findTopicById(@PathVariable Long id) {
        return topicService.findTopicById(id);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "topicList", allEntries = true)
    public ResponseEntity<TopicResponseDto> updateContentTopic(@PathVariable Long id,
                                                               @RequestBody @Valid TopicUpdateRequestDto topicUpdateRequestDto) {
        return ResponseEntity.ok(topicService.updateTopic(id, topicUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "topicList", allEntries = true)
    public void deleteTopicById(@PathVariable Long id) {
        topicService.deleteTopicById(id);
    }

}
