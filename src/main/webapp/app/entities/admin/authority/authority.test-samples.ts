import { IAuthority, NewAuthority } from './authority.model';

export const sampleWithRequiredData: IAuthority = {
  name: '7cc15259-08cb-4f24-8097-1b8db3c3d09d',
};

export const sampleWithPartialData: IAuthority = {
  name: 'f554a62c-7192-47ee-97a5-9f8d2c4880ec',
};

export const sampleWithFullData: IAuthority = {
  name: '9c1fe344-e13b-4224-8f42-5fca402f858c',
};

export const sampleWithNewData: NewAuthority = {
  name: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
