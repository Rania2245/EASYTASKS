package tn.itdevspace.easytask.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import tn.itdevspace.easytask.web.rest.TestUtil;

class EstimationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Estimation.class);
        Estimation estimation1 = new Estimation();
        estimation1.setId(UUID.randomUUID());
        Estimation estimation2 = new Estimation();
        estimation2.setId(estimation1.getId());
        assertThat(estimation1).isEqualTo(estimation2);
        estimation2.setId(UUID.randomUUID());
        assertThat(estimation1).isNotEqualTo(estimation2);
        estimation1.setId(null);
        assertThat(estimation1).isNotEqualTo(estimation2);
    }
}
