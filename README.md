
## Advent of Code 2022 Solutions in Kotlin

[![license](https://img.shields.io/github/license/tginsberg/advent-2022-kotlin)]()

This repo is my personal attempt at solving the [Advent of Code 2022](http://adventofcode.com/2022) set of problems with
the Kotlin programming language.

I am trying to solve these on the day they are posted with clear, idiomatic solutions. That means in some cases I will
sacrifice performance for a more clear solution. While I will endeavour to have these done day-of I can't promise it
because work and life can get in the way. Plus, some of these problems can get quite involved so solving it clearly and
writing up an explanation might take me longer than a day. We'll see how it goes! :)

Past years, also in Kotlin:

* 2017 - [GitHub](https://github.com/tginsberg/advent-2017-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2017/)
* 2018 - [GitHub](https://github.com/tginsberg/advent-2018-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2018/)
* 2019 - [GitHub](https://github.com/tginsberg/advent-2019-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2019/)
* 2020 - [GitHub](https://github.com/tginsberg/advent-2020-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2020/)
* 2021 - [GitHub](https://github.com/tginsberg/advent-2021-kotlin/)
  and [Blog Posts](https://todd.ginsberg.com/post/advent-of-code/2021/)

#### Daily Solution Index for 2022

| Day | Title                    | Links                                                                                                                                                                                                                                                                                                                   |
|-----|:-------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1   | Calorie Counting         | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day1/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day01.kt) [\[AoC\]](http://adventofcode.com/2022/day/1)                                                                          |
| 2   | Rock Paper Scissors      | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day2/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day02.kt) [\[AoC\]](http://adventofcode.com/2022/day/2)                                                                          |
| 3   | Rucksack Reorganization  | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day3/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day03.kt) [\[AoC\]](http://adventofcode.com/2022/day/3)                                                                          |
| 4   | Camp Cleanup             | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day4/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day04.kt) [\[AoC\]](http://adventofcode.com/2022/day/4) [\[Live Stream Recording\]](https://www.youtube.com/watch?v=dBIbr55YS0A) |
| 5   | Supply Stacks            | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day5/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day05.kt) [\[AoC\]](http://adventofcode.com/2022/day/5)                                                                          |
| 6   | Tuning Trouble           | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day6/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day06.kt) [\[AoC\]](http://adventofcode.com/2022/day/6)                                                                          |
| 7   | No Space Left On Device  | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day7/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day07.kt) [\[AoC\]](http://adventofcode.com/2022/day/7)                                                                          |
| 8   | Treetop Tree House       | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day8/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day08.kt) [\[AoC\]](http://adventofcode.com/2022/day/8)                                                                          |
| 9   | Rope Bridge              | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day9/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day09.kt) [\[AoC\]](http://adventofcode.com/2022/day/9)                                                                          |
| 10  | Cathode-Ray Tube         | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day10/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day10.kt) [\[AoC\]](http://adventofcode.com/2022/day/10)                                                                        |
| 11  | Monkey in the Middle     | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day11/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day11.kt) [\[AoC\]](http://adventofcode.com/2022/day/11)                                                                        |
| 12  | Hill Climbing Algorithm  | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day12/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day12.kt) [\[AoC\]](http://adventofcode.com/2022/day/12)                                                                        |
| 13  | Distress Signal          | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day13/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day13.kt) [\[AoC\]](http://adventofcode.com/2022/day/13)                                                                        |
| 14  | Regolith Reservoir       | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day14/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day14.kt) [\[AoC\]](http://adventofcode.com/2022/day/14)                                                                        |
| 15  | Beacon Exclusion Zone    | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day15/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day15.kt) [\[AoC\]](http://adventofcode.com/2022/day/15)                                                                        |
| 16  | Proboscidea Volcanium    | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day16/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day16.kt) [\[AoC\]](http://adventofcode.com/2022/day/16)                                                                        |
| 17  | Pyroclastic Flow         | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day17/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day17.kt) [\[AoC\]](http://adventofcode.com/2022/day/17)                                                                        |
| 18  | Boiling Boulders         | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day18/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day18.kt) [\[AoC\]](http://adventofcode.com/2022/day/18)                                                                        |
| 19  | Not Enough Minerals      | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day19/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day19.kt) [\[AoC\]](http://adventofcode.com/2022/day/19)                                                                        |
| 20  | Grove Positioning System | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day20/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day20.kt) [\[AoC\]](http://adventofcode.com/2022/day/20)                                                                        |
| 21  | Monkey Math              | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day21/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day21.kt) [\[AoC\]](http://adventofcode.com/2022/day/21)                                                                        |
| 22  | Monkey Map               | Coming soon. Ran out of time today for Part Two.                                                                                                                                                                                                                                                                        |
| 23  | Unstable Diffusion       | [\[Blog Post\]](https://todd.ginsberg.com/post/advent-of-code/2022/day23/) [\[Code\]](https://github.com/tginsberg/advent-2022-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2022/Day23.kt) [\[AoC\]](http://adventofcode.com/2022/day/23)                                                                        |

Copyright &copy; 2022 by Todd Ginsberg.

