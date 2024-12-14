package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"sort"
	"strconv"
	"strings"
)

func main() {
	file, err := os.Open("input")
	if err != nil {
		fmt.Printf("failed to open file: %v\n", err)
		return
	}
	defer file.Close()

	firstList, secondList, err := processFile(file)
	if err != nil {
		fmt.Printf("error processing file: %v\n", err)
		return
	}

	totalDistance := calculateTotalDistance(firstList, secondList)
	fmt.Printf("total distance: %d\n", totalDistance)

	totalSimilarityScore := calculateTotalSimilarityScore(firstList, secondList)
	fmt.Printf("total similarity score: %d\n", totalSimilarityScore)
}

func processFile(file *os.File) ([]int, []int, error) {
	scanner := bufio.NewScanner(file)
	var firstList, secondList []int

	for scanner.Scan() {
		line := scanner.Text()
		if firstNum, secondNum, err := parseLine(line); err == nil {
			firstList = append(firstList, firstNum)
			secondList = append(secondList, secondNum)
		}
	}

	if err := scanner.Err(); err != nil {
		return nil, nil, err
	}

	sort.Ints(firstList)
	sort.Ints(secondList)

	return firstList, secondList, nil
}

func parseLine(line string) (int, int, error) {
	parts := strings.Fields(line)
	if len(parts) != 2 {
		return 0, 0, fmt.Errorf("invalid line format: %s", line)
	}

	firstNum, err1 := strconv.Atoi(parts[0])
	secondNum, err2 := strconv.Atoi(parts[1])
	if err1 != nil || err2 != nil {
		return 0, 0, fmt.Errorf("error parsing numbers in line: %s", line)
	}

	return firstNum, secondNum, nil
}

func calculateTotalDistance(firstList, secondList []int) int {
	totalDistance := 0
	for i := 0; i < len(firstList); i++ {
		totalDistance += int(math.Abs(float64(firstList[i] - secondList[i])))
	}
	return totalDistance
}

func calculateTotalSimilarityScore(firstList, secondList []int) int {
	totalSimilarityScore := 0

	for i := 0; i < len(firstList); i++ {
		leftNum := firstList[i]
		rightNumCount := 0

		for j := 0; j < len(secondList); j++ {
			if secondList[j] == leftNum {
				rightNumCount++
			}
		}
		
		totalSimilarityScore += leftNum * rightNumCount
	}

	return totalSimilarityScore
}
	