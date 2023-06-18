<?php

$array = str_split($_GET['arr']);

$isfinished = 1;

for($i = 0; $i < 9; $i ++)
  if($array[$i] == "-")
    $isfinished = 0;

if($isfinished == 0){
  $index = rand(0, 8);
  while($array[$index] != "-")
    $index = rand(0, 8);

  $array[$index] = "0"; // facem mutarea calculatorului
}

$xwin = 0;
$owin = 0;


$win_combinations = array(
    array(0, 1, 2), // prima linie
    array(3, 4, 5), // a doua linie
    array(6, 7, 8), // a treia linie
    array(0, 3, 6), // prima coloană
    array(1, 4, 7), // a doua coloană
    array(2, 5, 8), // a treia coloană
    array(0, 4, 8), // diagonala principală
    array(2, 4, 6), // diagonala secundară
);

foreach ($win_combinations as $combination) {
  if ($array[$combination[0]] == 'X' && $array[$combination[1]] == 'X' && $array[$combination[2]] == 'X') {
    $xwin = 1;
    break;
  } elseif ($array[$combination[0]] == '0' && $array[$combination[1]] == '0' && $array[$combination[2]] == '0') {
    $owin = 1;
    break;
  }
}

if($isfinished == 0)
  $isfinished = 1;

for($i = 0; $i < 9; $i ++)
  if($array[$i] == "-")
    $isfinished = 0;

if($xwin == 1) echo "winx";
else if ($owin == 1) echo "win0";
else if($isfinished == 1) echo "nowin";
else echo $index;
?>