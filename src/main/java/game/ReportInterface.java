package game;

interface ReportInterface {
    void reportEdge(int firstPointNumber,int secondPointNumber);

    void reportOwner(int number, String playerName);

    void getExtraTurn();
}
