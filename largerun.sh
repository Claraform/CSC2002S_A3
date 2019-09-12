#!/bin/bash

make run arg1="largesample_input.txt" arg2="bigtest.txt"
diff largesample_output.txt bigtest.txt
