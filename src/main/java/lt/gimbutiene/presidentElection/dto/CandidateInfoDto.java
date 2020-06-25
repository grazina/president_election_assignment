package lt.gimbutiene.presidentElection.dto;

public class CandidateInfoDto extends CandidateBaseDto {
    private Long number;
    private String agenda;

    public Long getNumber() {
        return number;
    }

    public void setNumber(final Long number) {
        this.number = number;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(final String agenda) {
        this.agenda = agenda;
    }
}
