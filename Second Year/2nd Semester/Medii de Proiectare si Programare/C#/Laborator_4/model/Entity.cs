namespace model
{
    public interface Entity<ID>
    {
        ID Id { get; set; }
    }
}