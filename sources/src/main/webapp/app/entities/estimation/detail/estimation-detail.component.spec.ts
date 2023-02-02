import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EstimationDetailComponent } from './estimation-detail.component';

describe('Estimation Management Detail Component', () => {
  let comp: EstimationDetailComponent;
  let fixture: ComponentFixture<EstimationDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EstimationDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ estimation: { id: '9fec3727-3421-4967-b213-ba36557ca194' } }) },
        },
      ],
    })
      .overrideTemplate(EstimationDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(EstimationDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load estimation on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.estimation).toEqual(expect.objectContaining({ id: '9fec3727-3421-4967-b213-ba36557ca194' }));
    });
  });
});
