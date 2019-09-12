#!/bin/bash

make runpar arg1="largesample_input.txt" arg2="bigpartest.txt"
diff largesample_output.txt bigtest.txt
