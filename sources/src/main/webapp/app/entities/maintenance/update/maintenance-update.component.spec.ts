import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MaintenanceFormService } from './maintenance-form.service';
import { MaintenanceService } from '../service/maintenance.service';
import { IMaintenance } from '../maintenance.model';
import { IRessource } from 'app/entities/ressource/ressource.model';
import { RessourceService } from 'app/entities/ressource/service/ressource.service';

import { MaintenanceUpdateComponent } from './maintenance-update.component';

describe('Maintenance Management Update Component', () => {
  let comp: MaintenanceUpdateComponent;
  let fixture: ComponentFixture<MaintenanceUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let maintenanceFormService: MaintenanceFormService;
  let maintenanceService: MaintenanceService;
  let ressourceService: RessourceService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MaintenanceUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(MaintenanceUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MaintenanceUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    maintenanceFormService = TestBed.inject(MaintenanceFormService);
    maintenanceService = TestBed.inject(MaintenanceService);
    ressourceService = TestBed.inject(RessourceService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Ressource query and add missing value', () => {
      const maintenance: IMaintenance = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const ressource: IRessource = { id: '42684395-995b-4799-9483-7f9c42a6a3a8' };
      maintenance.ressource = ressource;

      const ressourceCollection: IRessource[] = [{ id: '9df351c0-277c-498d-84c6-369afbda9b2c' }];
      jest.spyOn(ressourceService, 'query').mockReturnValue(of(new HttpResponse({ body: ressourceCollection })));
      const additionalRessources = [ressource];
      const expectedCollection: IRessource[] = [...additionalRessources, ...ressourceCollection];
      jest.spyOn(ressourceService, 'addRessourceToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ maintenance });
      comp.ngOnInit();

      expect(ressourceService.query).toHaveBeenCalled();
      expect(ressourceService.addRessourceToCollectionIfMissing).toHaveBeenCalledWith(
        ressourceCollection,
        ...additionalRessources.map(expect.objectContaining)
      );
      expect(comp.ressourcesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const maintenance: IMaintenance = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
      const ressource: IRessource = { id: '11bf3eeb-a6a8-406c-aa5f-b0ef9449c483' };
      maintenance.ressource = ressource;

      activatedRoute.data = of({ maintenance });
      comp.ngOnInit();

      expect(comp.ressourcesSharedCollection).toContain(ressource);
      expect(comp.maintenance).toEqual(maintenance);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMaintenance>>();
      const maintenance = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(maintenanceFormService, 'getMaintenance').mockReturnValue(maintenance);
      jest.spyOn(maintenanceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ maintenance });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: maintenance }));
      saveSubject.complete();

      // THEN
      expect(maintenanceFormService.getMaintenance).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(maintenanceService.update).toHaveBeenCalledWith(expect.objectContaining(maintenance));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMaintenance>>();
      const maintenance = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(maintenanceFormService, 'getMaintenance').mockReturnValue({ id: null });
      jest.spyOn(maintenanceService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ maintenance: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: maintenance }));
      saveSubject.complete();

      // THEN
      expect(maintenanceFormService.getMaintenance).toHaveBeenCalled();
      expect(maintenanceService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMaintenance>>();
      const maintenance = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
      jest.spyOn(maintenanceService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ maintenance });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(maintenanceService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareRessource', () => {
      it('Should forward to ressourceService', () => {
        const entity = { id: '9fec3727-3421-4967-b213-ba36557ca194' };
        const entity2 = { id: '1361f429-3817-4123-8ee3-fdf8943310b2' };
        jest.spyOn(ressourceService, 'compareRessource');
        comp.compareRessource(entity, entity2);
        expect(ressourceService.compareRessource).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
