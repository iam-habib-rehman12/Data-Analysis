#create a NumPy array by passing tuple
import numpy as np

n = np.array((1,2,3))
print(n)
print(type(n))

#create a NumPy by passing list
n=np.array([1,2,3])
print(n)
# print(type(n))

#create a 0-dimensional array and check the dimension
n = np.array(25)
print(n)
# print(type(n))
print("Dimension = ", n.ndim)
print(n.shape)

#create a 1-dimensional array and check the dimension
n = np.array([10,20,30])
print(n)
# print(type(n))
print("Dimension = ", n.ndim)
print(n.shape)

#create a 2-dimensional array and check the dimension
n = np.array([[1,2,3] , [4,5,6] , [7,8,9]])
print(n)
# print(type(n))
print("Dimension = ", n.ndim)
print(n.shape)

#getting data types
n=np.array([['a','b','c'],['d','e','f']])
print(n.dtype)
n=np.array(["Habib","Sahil","Zeeshan"])
print(n.dtype)
n=np.array([1,2,3])
print(n.dtype)
n=np.array(["Habib","Sahil","Zeeshan"], dtype="S5")
print(n.dtype)

#Convert on type to another
n=np.array(['10','20','30','40','50'])
print(n)
print(n.dtype)

n2=n.astype('int')
print(n2)
print(n2.dtype)

#Array Creation Methods
zeros=np.zeros((3,4))
ones=np.ones((3,2))
rand=np.random.rand(2,2)
eye=np.eye(3,3)
print(zeros)
print(ones)
print(rand)
print(eye)

#Array Operations
a = np.array([1, 2, 3])
b = np.array([4, 5, 6])

print(a + b)
print(a * b)
print(a ** b)


#Indexing & Slicing
arr = np.array([7, 5, 13, 9, 11])
print(arr[3])
print(arr[0:3])

matrix = np.array([[1, 2, 3], [4, 5, 6]])
print(matrix[1, 1])

#Useful Functions
print(np.mean(arr))    # average
print(np.max(arr))     # max value
print(np.sum(arr))     # sum of all elements
print(np.sort(arr))    # sorted array

#Reshape array
arr=np.array([1, 2, 3,4, 5, 6,7,8])
reshaped=arr.reshape(2,4)
print(reshaped)

#Broadcasting
#It allows operations between arrays of different shapes.
a = np.array([1, 2, 3])
b = 2

print(a + b)  # [3 4 5] – scalar is "broadcasted" to each element
#Broadcasting in 2D:
A = np.array([[1, 2], [3, 4]])
B = np.array([10, 20])

print(A + B)

#Boolean Indexing/Filtering
arr = np.array([10, 20, 30, 40])
mask = arr > 20
print(mask)         # [False False  True  True]
print(arr[mask])    # [30 40]

# Or in one line:
print(arr[arr > 20])  # [30 40]

#Axis-Based Operation
matrix = np.array([[1, 2, 3],
                   [4, 5, 6]])

print(np.sum(matrix, axis=0))  # [5 7 9] column-wise sum
print(np.sum(matrix, axis=1))  # [6 15] row-wise sum

#Flatten, Ravel, and Transpose
matrix = np.array([[1,2],[3,4]])
flat=matrix.flatten()
print(flat)
transposed=matrix.transpose()
print(transposed)

#Stacking Arrays
a=np.array([1,2,3,4])
b=np.array([5,6,7,8])
print(np.concat((a,b)))
print(np.stack((a,b)))
print(np.vstack((a,b)))
print(np.hstack((a,b)))
print(np.dstack((a,b)))

#Splitting arrays
array=np.array([1,2,3,4,5,6,7,8])
print(np.split(array,2))

#Copy vs View
a = np.array([1, 2, 3])
b = a              # not a copy, same memory
b[0] = 100
print(a)
b[0] = 1

# To avoid this:
c = a.copy()       # now it's separate
c[0] = 999
print(a)
print(c)

#Normalize Data
#Normalize a list of numbers between 0 to 1
data= np.array([50,20,80,100])
max=data.max()
min=data.min()
normalized=(data-min)/(max-min)
print(normalized)

#How would you create a 5×5 matrix
#with random integers from 1 to 100?
arr = np.random.randint(1, 101, size=(5, 5))
print(arr)

#Conditional Logic
arr=np.array([10,25,30,5])
result=np.where(arr>20,1,0)
print(result)

#Handling Missing Data
data=np.array([10,20,30,np.nan,50])
print(np.isnan(data))
print(data[~np.isnan(data)])
print(np.nanmean(data))

data = np.array([5,10,15,20,25])
mean = np.mean(data)


