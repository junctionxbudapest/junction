<?php
$fields = ['key' => '99c5e07b4d5de9d18c350cdf64c5aa3dd', 'method' => 'getOrganization', 'inn' => '2309085638'];

$opts = [
    'http' => [
        'method' => 'GET',
        'header' => 'Content-type: application/x-www-form-urlencoded',
        'content' => http_build_query($fields)
    ]
];

$results = file_get_contents('http://20.71.140.143:6666/get_health_index_plot/login1', false, stream_context_create($opts));
file_put_contents("log2.jpg",$results);
?>