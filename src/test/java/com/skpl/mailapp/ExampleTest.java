package com.skpl.mailapp;
import com.skpl.mailapp.entity.Diary;
import com.skpl.mailapp.service.DiaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class ExampleTest {

    @Autowired
    private DiaryService diaryService;

    @Test
    public void test1() {
        List<Diary> diaries = diaryService.queryAll();
        List<Diary.DiaryToWeb> resDiaries = new ArrayList<>();
        for(Diary diary : diaries) {
            Diary.DiaryToWeb diaryToWeb = diary.toDiaryToWeb(diary);
            System.out.println(diaryToWeb.getD_time());
            resDiaries.add(diaryToWeb);
            System.out.println(diary.toDiaryToWeb(diary));
        }

    }

    @Test
    public void test2() {
        String encode = new String(Base64.getEncoder().encode("4564".getBytes()));
        System.out.println(encode);
        String string = new String(Base64.getDecoder().decode("NDU2NA=="));
        System.out.println(string);
    }



}
