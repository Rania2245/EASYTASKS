import dayjs from 'dayjs/esm';

import { Etat } from 'app/entities/enumerations/etat.model';

import { IActivite, NewActivite } from './activite.model';

export const sampleWithRequiredData: IActivite = {
  id: 20271,
  refAct: 'Gorgeous',
  description: 'Handcrafted',
  dateDebut: dayjs('2023-01-26'),
};

export const sampleWithPartialData: IActivite = {
  id: 5748,
  refAct: 'Generic standardizat',
  description: 'Pants magenta',
  dateDebut: dayjs('2023-01-25'),
  dateFin: dayjs('2023-01-26'),
};

export const sampleWithFullData: IActivite = {
  id: 56304,
  refAct: 'bypassing',
  description: 'benchmark system Timor-Leste',
  dateDebut: dayjs('2023-01-26'),
  dateFin: dayjs('2023-01-26'),
  raf: 'tan',
  etat: Etat['Termine'],
};

export const sampleWithNewData: NewActivite = {
  refAct: 'Nepalese black',
  description: 'bricks-and-clicks Infrastructure',
  dateDebut: dayjs('2023-01-25'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
