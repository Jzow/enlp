package ai.james.service;

import ai.james.common.constant.CorrectionConstants;
import com.alibaba.fastjson.JSONArray;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;

/**
 * @author James Zow
 * @version 1.0
 * @description: TODO 自然语言处理算法 interface层
 * @date 2021/11/26 13:56
 */
public interface NLPAlgorithmService {

    /**
     * 写作
     * @return
     */
    @Post(
            url = CorrectionConstants.WRITING_ALGORITHM_PROD_URL,
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: multipart/form-data; boundary=something"
            }
    )

    @Deprecated
    String writingAlgorithm(@Body("files") JSONArray object, @Body("percent")String percent, @Body("topic")String topic,
                            @Body("question")String question , @Body("topic_label")String topic_label, @Body("answer_request_word") String answer_request_word);

    // 文章：files
    // 分值权重： percent
    // 文本材料: topic
    // 题目要求：require 如果题库没有空 传null
    // 语言：language 1英文/2中文
    // 写作类型: write_type
    // 内容形式: disc_type_one
    // 词数：words_number 取最大值

    /**
     * 写作
     * @return
     */
    @Post(
            url = CorrectionConstants.WRITING_ALGORITHM_PROD_URL,
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: multipart/form-data; boundary=something"
            }
    )
    String writingV3Algorithm(@Body("files") JSONArray object, @Body("percent")String percent, @Body("topic")String topic, @Body("require")String require,
                              @Body("language")Integer language, @Body("write_type")String write_type , @Body("disc_type_one")String disc_type_one,
                              @Body("words_number") String words_number);



    /**
     * 词汇
     * @return answer_list
     */
    @Post(
            url = CorrectionConstants.WRITING_V2_ALGORITHM_PROD_URL,
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: multipart/form-data; boundary=something"
            }
    )
    String writingV2TestAlgorithm(@Body("object_info") JSONArray dataObject);

    /**
     * 词汇测试
     * @return answer_list
     */
    @Post(
            url = CorrectionConstants.WRITING_V2_TEST_ALGORITHM_PROD_URL,
            headers = {
                    "Accept-Charset: utf-8",
                    "Content-Type: multipart/form-data; boundary=something"
            }
    )
    String V2TestAlgorithm(@Body("object_info") JSONArray dataObject);
}
