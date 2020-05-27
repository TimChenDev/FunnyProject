package com.need.path.to;

import com.need.path.to.IWorkerCallback;

/**
 *  author: Tim Chen
 *  time  : 2020-04-30
 *  desc  :
 *  這是一個工具人
 *  定義工具人要做的事情有哪些項目
 */
interface IWorker {

    // 打掃房間
    void cleanRoom();

    // 組電腦, 需提供菜單需求
    void buildComputer(String _order);

    // 加賴, 留下聯絡方式, 做完事情才能通知對方
    void addLine(IWorkerCallback _callback);
}