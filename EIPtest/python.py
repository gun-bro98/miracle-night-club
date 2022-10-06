a = 100
result = 0
for i in range(1,3):
    result = a >> i
    result = result + 1
print(result)
# 시프트 문제 >>가 시프트 연산인 것을 잘 기억!