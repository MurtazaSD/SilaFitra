import { Moment } from 'moment';

export interface IFamily {
  id?: number;
  hofEjamaatId?: number;
  hofFullName?: string;
  emailAddress?: string;
  phoneNumber?: string;
  totalGents?: number;
  totalLadies?: number;
  totalKids?: number;
  totalPregnants?: number;
  totalDecesead?: number;
  zakatulFitr?: number;
  najwatusukr?: number;
  khumus?: number;
  silatulImam?: number;
  nazrulMaqam?: number;
  lastUpdateTstamp?: Moment;
  submittedBy?: string;
}

export const defaultValue: Readonly<IFamily> = {};
