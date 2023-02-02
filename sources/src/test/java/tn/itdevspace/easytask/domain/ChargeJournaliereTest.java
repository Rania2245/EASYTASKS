package tn.itdevspace.easytask.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import tn.itdevspace.easytask.web.rest.TestUtil;

class ChargeJournaliereTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChargeJournaliere.class);
        ChargeJournaliere chargeJournaliere1 = new ChargeJournaliere();
        chargeJournaliere1.setId(UUID.randomUUID());
        ChargeJournaliere chargeJournaliere2 = new ChargeJournaliere();
        chargeJournaliere2.setId(chargeJournaliere1.getId());
        assertThat(chargeJournaliere1).isEqualTo(chargeJournaliere2);
        chargeJournaliere2.setId(UUID.randomUUID());
        assertThat(chargeJournaliere1).isNotEqualTo(chargeJournaliere2);
        chargeJournaliere1.setId(null);
        assertThat(chargeJournaliere1).isNotEqualTo(chargeJournaliere2);
    }
}
