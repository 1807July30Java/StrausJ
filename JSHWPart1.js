const homework = {};

/*
 1. Return the nth fibonacci number

 f(0) = 0
 f(1) = 1
 f(10) = 55
*/
homework.fibonacci = function (n) {
    if (n === 0) {
        return 0;
    } else if (n === 1) {
        return 1;
    } else {
        let last = 0;
        let current = 1;
        let temp;
        for (let i = 1; i < n; i++) {
            temp = current;
            current += last;
            // console.log(current);
            last = temp;
            // console.log(last);
        }
        return current;
    }
};

/*
 2. Sort array of integers

 f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

 Don't use the Array sort() method... that would be lame.
*/
homework.sort = function (array) {
    const returnArray = [];
    let min;
    let index;
    for (let i = 0; i < array.length; i++) {
        min = Math.min.apply(null, array);
        index = array.indexOf(min);
        returnArray[i] = array[index];
        array[index] = Math.max.apply(null, array) + 1;
    }

    return returnArray;
};

/*
 3. Return the factorial of n

 f(0) = 1
 f(1) = 1
 f(3) = 6
*/
homework.factorial = function (n) {
    let total = 1;
    for (let i = 1; i <= n; i++) {
        total *= i;
    }
    return total;
};

/*
 4. Rotate left

 Given array, rotate left n times and return array

 f([1,2,3,4,5], 1) = [2,3,4,5,1]
 f([1,2,3,4,5], 6) = [2,3,4,5,1]
 f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
homework.rotateLeft = function (array, n) {
    const realRotate = n % array.length;
    return array.slice(realRotate, array.length).concat(array.slice(0, realRotate));
};

/*
 5. Balanced Brackets

 A bracket is any one of the following: (, ), {, }, [, or ]

 The following are balanced brackets:
    ()
    ()()
    (())
    ({[]})

 The following are NOT balanced brackets:
 (
 )
 (()
 ([)]

 Return true if balanced
 Return false if not balanced
*/
homework.balancedBrackets = function (bracketsString) {
    let parenthesis = 0;
    let square = 0;
    let curl = 0;

    for (let i = 0; i < bracketsString.length; i++) {
        if (parenthesis < 0 || square < 0 || curl < 0) {
            return false;
        } else {
            switch (bracketsString[i]) {
                case '(':
                    parenthesis++;
                    break;
                case ')':
                    parenthesis--;
                    break;
                case '{':
                    curl++;
                    break;
                case '}':
                    curl--;
                    break;
                case '[':
                    square++;
                    break;
                case ']':
                    square--;
                    break;

            }
        }
    }

    return parenthesis === 0 && square === 0 && curl === 0;
};