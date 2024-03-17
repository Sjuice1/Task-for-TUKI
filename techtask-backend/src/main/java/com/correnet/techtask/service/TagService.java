package com.correnet.techtask.service;

import com.correnet.techtask.domain.Image;
import com.correnet.techtask.domain.Tag;
import com.correnet.techtask.repository.ImageRepository;
import com.correnet.techtask.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public Tag saveTag(Tag tag){
        return tagRepository.save(tag);
    }

    public List<Tag> getTagsByName(String tagName) {
        return tagRepository.findAllByName(tagName);
    }
}
