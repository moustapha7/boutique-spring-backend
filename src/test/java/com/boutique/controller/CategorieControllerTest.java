package com.boutique.controller;

import com.boutique.BoutiqueAngularSpringApplication;
import com.boutique.model.Categorie;
import com.boutique.repository.CategorieRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest(classes = BoutiqueAngularSpringApplication.class)
@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@WithMockUser
class CategorieControllerTest {


    @Autowired
     MockMvc mockMvc;

    @MockBean
     CategorieRepository categorieRepository;

    @Before
    public void createCateg()
    {
        Categorie categorie = new Categorie();
        categorie.setId(1);
        categorie.setCode("Cat_1");
        categorie.setLibelle("Testaa");
        categorie.setUsername("admin");
        categorieRepository.save(categorie);
    }

    @Test
    public void testCount() throws Exception {


        Mockito.when(categorieRepository.count()).thenReturn(1L);
         mockMvc.perform(MockMvcRequestBuilders.get("/nombreCategories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(categorieRepository).count();
    }

    @Test
    public void testGetAllCategories() throws Exception {

        Mockito.when(categorieRepository.count()).thenReturn(4L);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/nombreCategories")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());
        Mockito.verify(categorieRepository).count();

    }

}