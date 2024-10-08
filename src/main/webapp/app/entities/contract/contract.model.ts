import dayjs from 'dayjs/esm';

export interface IContract {
  id: number;
  startDate?: dayjs.Dayjs | null;
  endDate?: dayjs.Dayjs | null;
  status?: string | null;
}

export type NewContract = Omit<IContract, 'id'> & { id: null };
