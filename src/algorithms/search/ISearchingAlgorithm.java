package algorithms.search;

public interface ISearchingAlgorithm {
    void setTotalVertex(int totalVertex);

    /** The method returns the number of vertex that the we should visit to find the goal path
     * @return a AState that represent the all phat-the gold path of the solution
     */
    int getNumberOfNodesEvaluated();

    /** The method returns the name of the Algorithm
     * @return a AState that represent the all phat-the gold path of the solution
     */
    String getName();
    /** The method returns the solution to the problem
     * @return a AState that represent the all phat-the gold path of the solution
     */
     Solution solve(ISearchable TheProblem);

}
