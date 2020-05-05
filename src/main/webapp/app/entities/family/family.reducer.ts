import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFamily, defaultValue } from 'app/shared/model/family.model';

export const ACTION_TYPES = {
  FETCH_FAMILY_LIST: 'family/FETCH_FAMILY_LIST',
  FETCH_FAMILY: 'family/FETCH_FAMILY',
  CREATE_FAMILY: 'family/CREATE_FAMILY',
  UPDATE_FAMILY: 'family/UPDATE_FAMILY',
  DELETE_FAMILY: 'family/DELETE_FAMILY',
  RESET: 'family/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFamily>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type FamilyState = Readonly<typeof initialState>;

// Reducer

export default (state: FamilyState = initialState, action): FamilyState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FAMILY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FAMILY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_FAMILY):
    case REQUEST(ACTION_TYPES.UPDATE_FAMILY):
    case REQUEST(ACTION_TYPES.DELETE_FAMILY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_FAMILY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FAMILY):
    case FAILURE(ACTION_TYPES.CREATE_FAMILY):
    case FAILURE(ACTION_TYPES.UPDATE_FAMILY):
    case FAILURE(ACTION_TYPES.DELETE_FAMILY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_FAMILY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_FAMILY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_FAMILY):
    case SUCCESS(ACTION_TYPES.UPDATE_FAMILY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_FAMILY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/families';

// Actions

export const getEntities: ICrudGetAllAction<IFamily> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FAMILY_LIST,
  payload: axios.get<IFamily>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IFamily> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FAMILY,
    payload: axios.get<IFamily>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IFamily> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FAMILY,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFamily> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FAMILY,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFamily> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FAMILY,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
