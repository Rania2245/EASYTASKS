import { IRessource, NewRessource } from './ressource.model';

export const sampleWithRequiredData: IRessource = {
  id: 'c78bae1d-286a-41fb-8b0b-798bcbc837e0',
  nom: 'AI compressing Prairie',
  prenom: 'HDD',
};

export const sampleWithPartialData: IRessource = {
  id: '581dde19-78ea-4804-8ac2-bebc8c7862ae',
  nom: 'clicks-and-mortar Factors 4th',
  prenom: 'Account',
};

export const sampleWithFullData: IRessource = {
  id: '74b6a3c3-160e-4888-a002-1618a2573ca6',
  nom: 'systemic Licensed',
  prenom: 'matrix auxiliary transmitting',
};

export const sampleWithNewData: NewRessource = {
  nom: 'bypassing',
  prenom: 'technologies revolutionize Licensed',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
