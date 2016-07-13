<?php

$time = date("Y-m-d H:i:s");
$order_status = array("processing", "shipped", "delivered");
$order_id = "xxxxx-xxx";

$part = 1;

for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
$part++;
for ($i = 0; $i < 100000; $i++) {
    $time = date("Y-m-d H:i:s");
    $od = $order_status[array_rand($order_status, 1)];
    $text = "$time,$order_id,$od\n";
    file_put_contents("part_$part.csv", $text, FILE_APPEND);
    #sleep(rand(0, 1));
}
