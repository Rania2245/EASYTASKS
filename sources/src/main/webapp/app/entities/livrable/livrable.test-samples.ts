import dayjs from 'dayjs/esm';

import { Etat } from 'app/entities/enumerations/etat.model';

import { ILivrable, NewLivrable } from './livrable.model';

export const sampleWithRequiredData: ILivrable = {
  id: 96229,
  refLivrable: 'Data Directives Rubb',
  dateDebut: dayjs('2023-01-26'),
  description: 'yellow',
};

export const sampleWithPartialData: ILivrable = {
  id: 86756,
  refLivrable: 'Administrator Rubber',
  dateDebut: dayjs('2023-01-25'),
  description: 'Senior Garden',
  etat: Etat['EnCours'],
};

export const sampleWithFullData: ILivrable = {
  id: 91247,
  refLivrable: 'Direct',
  dateDebut: dayjs('2023-01-25'),
  dateFin: dayjs('2023-01-25'),
  description: 'Wooden payment',
  etat: Etat['Termine'],
};

export const sampleWithNewData: NewLivrable = {
  refLivrable: 'website',
  dateDebut: dayjs('2023-01-25'),
  description: 'National Licensed',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
