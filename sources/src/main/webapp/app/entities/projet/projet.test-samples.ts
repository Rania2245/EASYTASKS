import dayjs from 'dayjs/esm';

import { Type } from 'app/entities/enumerations/type.model';
import { Etat } from 'app/entities/enumerations/etat.model';

import { IProjet, NewProjet } from './projet.model';

export const sampleWithRequiredData: IProjet = {
  id: 59803,
  refProjet: 'Communications inter',
  type: Type['Interne'],
  description: 'Account',
  datedebut: dayjs('2023-01-26'),
};

export const sampleWithPartialData: IProjet = {
  id: 92611,
  refProjet: 'IB',
  type: Type['Interne'],
  description: 'Kids white Account',
  datedebut: dayjs('2023-01-26'),
  etat: Etat['Planifier'],
};

export const sampleWithFullData: IProjet = {
  id: 68792,
  refProjet: 'optical deposit band',
  type: Type['Externe'],
  description: 'parsing Berkshire cultivate',
  datedebut: dayjs('2023-01-25'),
  datefin: dayjs('2023-01-26'),
  etat: Etat['Termine'],
};

export const sampleWithNewData: NewProjet = {
  refProjet: 'wireless',
  type: Type['Interne'],
  description: 'orchid Creative program',
  datedebut: dayjs('2023-01-25'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
