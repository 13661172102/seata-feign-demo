package com.teamer.serviceat.service;

import java.util.Map;

public interface AtService {

    String insert(Map<String, String> params);

    String update(Map<String, String> params);
}
