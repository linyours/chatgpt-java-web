package com.blue.cat.bean.gpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liuzilin
 * @date 2023/5/5 11:22 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class drawImageRes {

    private String authToken;

    private List<String> taskIds;

}
