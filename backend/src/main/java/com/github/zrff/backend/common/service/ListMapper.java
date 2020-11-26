package com.github.zrff.backend.common.service;

import java.util.List;

public interface ListMapper<T> {
    T map(List<Object> list,Object listSize);
}
