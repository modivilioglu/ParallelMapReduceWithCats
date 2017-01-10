# parallel-mapreduce-with-cats-library
This project is a minimalist example of how an RDD like paralel collection work,
that do parallel operations on a single collection, and how they hide the implementation detail
from the user.

### Power of cats library and type classes
Also the power of cats library is demonstrated in the example shown. The implicit conversions and type classes
are used along with Cats' Show[] typeclass, giving line texts a new ability to calculate the statistics of words and letters
they posess.

### Algorithm
The parallel operations, should be associative, and the reason is concretely visible in the code.
Basically the algorithm is as follows:
- Partition the data (as in RDD)
- For each partition, create a new paralell task, and map the partition with the (func) function
- Collect the future result in the same order
- And apply the |+| monadic accumulate function, served as the reduce operation in the RDDs.

