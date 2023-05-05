package com.chen.sbbus.utils.Re;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.sbbus.entity.Routes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoutesResponse {
    IPage<Routes> routesPage;
    List<String> routesName;
}
