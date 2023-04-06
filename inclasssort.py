def main():
    # a = [1,3,6]
    a = [6,1,5,2,4,3]
    for i in range(0,len(a),2):
        max=i
        min=i
        for j in range(i,len(a)):
            if a[i] < a[j]: max=j
        print("max",max)
        tmp = a[max]
        a[i] = a[max]
        
        for k in range(i,len(a)):
            if a[i] < a[k]: min=k
        print("min",min)
main()