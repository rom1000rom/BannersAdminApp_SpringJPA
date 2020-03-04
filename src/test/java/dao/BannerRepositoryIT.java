package dao;


import boot.App;
import boot.dao.BannerRepository;
import boot.model.Banner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/*Используя специальный runner  SpringJUnit4ClassRunner, мы
 инициализируем Spring контест автоматически при запуске теста*/
@RunWith(SpringJUnit4ClassRunner.class)
/*Аннотация  @ContextConfiguration указывает, как именно мы хотим
сконфигурировать контекст.*/
@ContextConfiguration(loader= AnnotationConfigContextLoader.class,
        classes = App.class)
@ActiveProfiles("test")//Активизируем профиль для тестирования

/*Помечает контекст как "грязный", в режиме BEFORE_EACH_TEST_METHOD
он будет сбрасываться передкаждым тестом*/
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BannerRepositoryIT
{
     @Autowired
     BannerRepository bannerRepository;

    @Before
    @Transactional
    public void setUp() throws Exception
    {
        bannerRepository.save(new Banner("TEST", 100, 100,
                "TEST", "TEST"));
        bannerRepository.save(new Banner("TEST2", 200, 200,
                "TEST2", "TEST2"));
        bannerRepository.save(new Banner("TEST3", 300, 300,
                "TEST3", "TEST3"));

    }

    @Test
    public void testFindByTargetUrlLike()
    {
        Banner expected = new Banner("TEST2", 200, 200,
                "TEST2", "TEST2");
        expected.setBannerId(2);

        assertEquals(expected, bannerRepository.findByTargetUrlLike("TEST2").get(0));
    }

    @Test
    public void testFind()
    {
        Banner expected = new Banner("TEST", 100, 100,
                "TEST", "TEST");
        expected.setBannerId(1);

        assertEquals(expected, bannerRepository.findByTargetUrlLike("TEST").get(0));
    }

    @Test
    public void testFindSec()
    {
        Banner expected = new Banner("TEST3", 300, 300,
                "TEST3", "TEST3");
        expected.setBannerId(3);

        assertEquals(expected, bannerRepository.findByTargetUrlLike("TEST3").get(0));
    }

}
