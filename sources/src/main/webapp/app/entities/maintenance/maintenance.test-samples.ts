import dayjs from 'dayjs/esm';

import { Etat } from 'app/entities/enumerations/etat.model';

import { IMaintenance, NewMaintenance } from './maintenance.model';

export const sampleWithRequiredData: IMaintenance = {
  id: 'a3dd5506-2f11-4080-a3b1-28ee65334a39',
  description: 'Greece',
  dateDebut: dayjs('2023-01-26'),
};

export const sampleWithPartialData: IMaintenance = {
  id: 'e5d49800-63d0-4ec1-b723-6bb58343617f',
  description: 'PNG Cross-group',
  produit: 'Cambridgeshire',
  solution: 'Integration Bike',
  etat: Etat['Termine'],
  dateDebut: dayjs('2023-01-25'),
  dateFin: dayjs('2023-01-25'),
  duree: 80418,
};

export const sampleWithFullData: IMaintenance = {
  id: '65968d5e-d71a-4fa5-a4cb-b77ff66e1ad5',
  description: 'Future array bus',
  produit: 'deposit',
  solution: 'Extension blockchains azure',
  etat: Etat['EnCours'],
  dateDebut: dayjs('2023-01-25'),
  dateFin: dayjs('2023-01-25'),
  duree: 97153,
};

export const sampleWithNewData: NewMaintenance = {
  description: 'Kentucky deposit wireless',
  dateDebut: dayjs('2023-01-25'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
