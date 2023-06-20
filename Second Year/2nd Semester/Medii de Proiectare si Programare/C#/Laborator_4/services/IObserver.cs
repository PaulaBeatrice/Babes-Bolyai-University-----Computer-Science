using model;

namespace services
{
    public interface IObserver
    {
        void notifyAddedPoints(Participant participant);
    }
}