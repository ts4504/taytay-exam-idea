package com.athome.service.impl;

import com.athome.dto.AutoPaperRequest;
import com.athome.dto.ManualPaperRequest;
import com.athome.dto.TypeCountDTO;
import com.athome.mapper.PaperMapper;
import com.athome.mapper.PaperQuestionMapper;
import com.athome.mapper.QuestionMapper;
import com.athome.pojo.Paper;
import com.athome.pojo.PaperQuestion;
import com.athome.pojo.Question;
import com.athome.pojo.Result;
import com.athome.service.PaperService;
import com.athome.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private PaperQuestionMapper paperQuestionMapper;

    //手动组卷
    @Transactional
    @Override
    public Paper manualAddPaper(ManualPaperRequest request) {
        //获取用户id
        Map<String,Object> cliam = ThreadLocalUtil.get();
        Integer teacherId = (Integer) cliam.get("id");
        //为Paper对象赋值
        Paper paper = new Paper();
        paper.setPaperName(request.getPaperName());
        paper.setDescription(request.getDescription());
        paper.setCourseId(request.getCourseId());
        paper.setTeacherId(teacherId);
        paper.setPaperType("手动组卷");
        paper.setCreateTime(LocalDateTime.now());
        paper.setUpdateTime(LocalDateTime.now());
        //添加进数据库中//主键回显
        paperMapper.add(paper);

        Double totalScore = 0.0;
        PaperQuestion pq = new PaperQuestion();
        //遍历添加的试卷试题关联类
        for(Integer questionId:request.getQuestions()){
            Question question = questionMapper.findById(questionId);
            //主键回显拿到了，不用担心
            pq.setPaperId(paper.getId());
            pq.setQuestionId(questionId);

            paperQuestionMapper.add(pq);
            //获取题目的建议分值
            //累加总分
            totalScore += question.getScore();
        }
        //将总分更新
        paper.setTotalScore(totalScore);
        paperMapper.update(paper);
        return paper;
    }

    @Transactional
    @Override
    public Paper autoAddPaper(AutoPaperRequest request) {
        //获取用户id
        Map<String,Object> cliam = ThreadLocalUtil.get();
        Integer teacherId = (Integer) cliam.get("id");
        //为Paper对象赋值
        Paper paper = new Paper();
        paper.setPaperName(request.getPaperName());
        paper.setDescription(request.getDescription());
        paper.setCourseId(request.getCourseId());
        paper.setTeacherId(teacherId);
        paper.setPaperType("自动组卷");
        paper.setCreateTime(LocalDateTime.now());
        paper.setUpdateTime(LocalDateTime.now());
        //添加进数据库中//主键回显
        paperMapper.add(paper);

        Double totalScore = 0.0;//试卷总分
        int orderInPaper = 1;//题目顺序
        PaperQuestion pq = new PaperQuestion();
        for(TypeCountDTO tc:request.getTypeCounts()){
            //获取指定个数类型的题目集合
            List<Question> questions = questionMapper.findByLimitRand(paper.getCourseId(),tc.getType(),tc.getCount());
            //遍历这些题目，将其添加进试卷试题关联表中,题号按照递增累加

            for(Question q:questions){
                pq.setPaperId(paper.getId());
                pq.setQuestionId(q.getId());
                pq.setOrderInPaper(orderInPaper++);
                pq.setQuestionScore(q.getScore());
                totalScore += q.getScore();
                paperQuestionMapper.add(pq);
            }
        }
        paper.setTotalScore(totalScore);
        paperMapper.update(paper);
        return paper;
    }

    @Override
    public List<Paper> list() {
        return paperMapper.list();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        paperMapper.delete(id);
    }

    @Override
    public List<Paper> findByCourse(Integer courseId) {
        return paperMapper.findByCourse(courseId);
    }


}
