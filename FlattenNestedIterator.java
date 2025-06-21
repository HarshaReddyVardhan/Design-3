// Time Complexity:
// Each NestedInteger element is visited exactly once, so the total time across all calls to hasNext() and next() is O(N) where N = total number of integers in all nested lists.

// Space Complexity:
// In the worst case, you may store O(D) stack frames where D = maximum nesting depth (due to the stack of iterators).

// Approach in 3 sentences:
// We use a stack of Iterator<NestedInteger> to simulate recursive traversal through a nested list. 
// In hasNext(), we unwrap nested lists until we find an integer, which we cache in nextEle. 
// Then next() simply returns this cached integer.

  
public class NestedIterator implements Iterator<Integer> {
    NestedInteger nextEle;
    Stack<Iterator<NestedInteger>> st;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEle.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            } else if( (nextEle = st.peek().next()).isInteger()){
                return true;
            } else{
                st.push(nextEle.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
