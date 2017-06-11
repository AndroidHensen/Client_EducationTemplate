package com.hensen.teachercontent.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hensen.shixiongsdk.BaseTemplate.BaseActivity;
import com.hensen.shixiongsdk.Widght.ExtendListView;
import com.hensen.teachercontent.Adapter.CareerAdapter;
import com.hensen.teachercontent.Bean.Career;
import com.hensen.teachercontent.R;

import java.util.ArrayList;
import java.util.List;

public class CareerActivity extends BaseActivity {

    private ExtendListView lv_career;
    private CareerAdapter adapter;
    private List<Career> careerList;

    private String[] title = {"MBTI职业性格测试完整版", "霍兰德职业兴趣测试完整版测试", "DISC性格测评", "职业锚测评", "职业满意度测试", "求职能力测试", "职业压力测试", "创业可行性测试"};
    private String[] content = {"MBTI的全名是Myers-Briggs Type Indicator。它是一种迫选型、自我报告式的性格评估理论模型，用以衡量和描述人们在获取信息、作出决策、对待生活等方面的心理活动规律和性格类型。",
            "霍兰德职业兴趣测试完整版测试由美国著名职业指导专家霍兰德编制，主要用于确定被测试者的职业兴趣倾向,进而用于指导被测试者选择适合自身职业兴趣的专业发展方向和职业发展方向。",
            "DISC性格测评不是一个测试，而是一个工具.测试经常由其他人为你打分评级，因此他们总是对你的情况指指点点。DiSC性格测评为你所用，为你服务请放轻松吧！",
            "职业锚是指当一个人面临职业选择的时候，他无论如何都不会放弃的职业中至关重要的东西或价值观。研究表明职业锚是内心深处对自己的看法，它是自己的才干、价值观、动机经过自省后形成的，职业锚可以指导、约束、或稳定个人的职业生涯。",
            "MBTI的全名是Myers-Briggs Type Indicator。它是一种迫选型、自我报告式的性格评估理论模型，用以衡量和描述人们在获取信息、作出决策、对待生活等方面的心理活动规律和性格类型。",
            "本求职能力测试是由向阳生涯管理咨询集团的职业规划师团队凝结11年的职业规划咨询和求职辅导经验而精心设计。旨在帮助求职正确认识自身的求职能力，并有针对性地作出调整。",
            "压力既可以是你生活的助手也可以变成生活的阻碍。职业压力自我简易诊断，职业倦怠综合征诊断系列量表之一，帮助你了解自己面临的心理压力程度。",
            "确定创业，但是要从哪些方面来考虑创业项目的可行性，怎么样确定是不是可以创业？应该用什么样的标准来衡量？请选择较适合你的选项"};
    private int[] imgId = {R.drawable.ic_career_img_1, R.drawable.ic_career_img_2, R.drawable.ic_career_img_3, R.drawable.ic_career_img_4,
            R.drawable.ic_career_img_5, R.drawable.ic_career_img_6, R.drawable.ic_career_img_7, R.drawable.ic_career_img_8,};
    private String[] urls = {"http://www.xycareer.com/mbti/", "http://www.xycareer.com/hollander/",
            "http://www.xycareer.com/disc/", "http://www.xycareer.com/anchor/",
            "http://www.xycareer.com/satisfy/", "http://www.xycareer.com/ability/",
            "http://www.xycareer.com/stress/", "http://www.xycareer.com/feasibility/"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_career;
    }

    @Override
    public void initViews() {
        lv_career = findView(R.id.lv_career);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        setTitle("职业测评");
        setTitleCanBack();

        initCareerViews();
    }

    @Override
    public void processClick(View v) {
    }


    /**
     *
     */
    private void initCareerViews() {
        careerList = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            careerList.add(new Career(title[i], content[i], imgId[i], urls[i]));
        }
        adapter = new CareerAdapter(this, careerList);
        lv_career.setAdapter(adapter);
    }
}
