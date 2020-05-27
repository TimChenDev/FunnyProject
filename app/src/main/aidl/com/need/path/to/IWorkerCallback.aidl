package com.need.path.to;

/**
 *  author: Tim Chen
 *  time  : 2020-04-30
 *  desc  :

 *  這裡定義工具人回報機制
 */
interface IWorkerCallback {

    // 工具人回報房間打掃完畢
    void finishCleanRoom();

    // 工具人回報電腦組好了
    void finishComputer();
}