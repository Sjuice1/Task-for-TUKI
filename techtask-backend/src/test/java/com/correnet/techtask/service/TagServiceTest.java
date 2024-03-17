package com.correnet.techtask.service;

import com.correnet.techtask.domain.Tag;
import com.correnet.techtask.repository.TagRepository;
import com.correnet.techtask.util.TestDataGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class TagServiceTest {
    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;

    @Test
    public void Should_return_tag_when_save_tag(){
        Tag tag = TestDataGenerator.generateTag();

        when(tagRepository.save(tag)).thenReturn(tag);

        Tag answerTag = tagService.saveTag(tag);

        assertEquals(tag,answerTag);
    }

    @Test
    public void Should_return_tags_when_get_tags_by_name(){
        String tagName = "someTag";
        List<Tag> tags = TestDataGenerator.generateListOfTags();

        when(tagService.getTagsByName(tagName)).thenReturn(tags);

        List<Tag> answerTag = tagService.getTagsByName(tagName);

        assertEquals(3,answerTag.size());
    }
}
