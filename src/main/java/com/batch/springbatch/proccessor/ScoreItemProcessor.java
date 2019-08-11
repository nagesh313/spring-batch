package com.batch.springbatch.proccessor;

import com.batch.springbatch.entity.Score;
import com.batch.springbatch.entity.ScoreRaw;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

import java.util.Date;

public class ScoreItemProcessor implements ItemProcessor<ScoreRaw, Score> {
    @Override
    public Score process(ScoreRaw scoreRaw) throws Exception {
        Score score = new Score();
        if (!StringUtils.isEmpty(scoreRaw.getRound()) && !scoreRaw.getRound().equals("?")) {
            try {
                score.setRound(Long.parseLong(scoreRaw.getRound()));
            } catch (Exception e) {

            }

        }
        score.setTeam1(scoreRaw.getTeam1().split("\\(")[0]);
        score.setTeam2(scoreRaw.getTeam2().split("\\(")[0]);
        score.setHalfTimeScore(scoreRaw.getHt());
        score.setFullTimeScore(scoreRaw.getFt());
        try {
            score.setDate(new Date(scoreRaw.getDate().split("\\)")[1].split("\\(")[0].trim()));
        } catch (Exception e) {
            //System.out.println("Wrong Date " + scoreRaw.getDate());
        }
        return score;
    }
}
