package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Mono;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Ignore
public class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    @InjectMocks
    ImageController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();

    }

    @Test
    public void getImageGet() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId("1"); // Set it to a long of 1

        when(recipeService.findCommandById(anyString())).thenReturn(Mono.just(command));

        //when
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyString());
    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Spring Framework Guru".getBytes());

        when(imageService.saveImageFile(anyString(), any())).thenReturn(Mono.empty());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyString(), any());
    }

    @Test
    public void renderImageFromDB() throws Exception {

//        Broken due to the removal of MVC and migration to reactive framework
//        given
//        RecipeCommand command = new RecipeCommand();
//        command.setId("1");
//
//        String s = "fake image text";
//        Byte[] bytesBoxed = new Byte[s.getBytes().length];
//
//        int i = 0;
//
//        for (byte primByte: s.getBytes()){
//            bytesBoxed[i++] = primByte;
//        }
//
//        command.setImage(bytesBoxed);
//
//        when(recipeService.findCommandById(anyString())).thenReturn(Mono.just(command));
//
//        //when
//        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
//                .andExpect(status().isOk())
//                .andReturn().getResponse();
//
//        byte[] responseBytes = response.getContentAsByteArray();
//
//        assertEquals(s.getBytes().length, responseBytes.length);
//    }

//    @Test -- Removed this test because it was just a demo
//    public void testImageFormatExceptionHandle() throws Exception {
//        mockMvc.perform(get( "/recipe/asdf/recipeimage"))
//                .andExpect(status().isBadRequest())
//                .andExpect(view().name("400error"));
//    }

    }
}