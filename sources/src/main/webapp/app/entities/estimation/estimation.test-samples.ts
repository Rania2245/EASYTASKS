import dayjs from 'dayjs/esm';

import { TypeEntite } from 'app/entities/enumerations/type-entite.model';

import { IEstimation, NewEstimation } from './estimation.model';

export const sampleWithRequiredData: IEstimation = {
  id: '3a83931e-1016-449f-8c68-e7ae645ed49e',
};

export const sampleWithPartialData: IEstimation = {
  id: 'b5c3e4b8-4d9f-4aef-b317-7935bb635d6d',
  valeurJour: 62492,
  priseEnCharge: false,
  type: TypeEntite['Projet'],
};

export const sampleWithFullData: IEstimation = {
  id: '970defa5-8759-454a-88df-2f3ce4849f56',
  date: dayjs('2023-01-25'),
  valeurJour: 90336,
  valeurHeure: 7250,
  priseEnCharge: false,
  type: TypeEntite['Activite'],
};

export const sampleWithNewData: NewEstimation = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
