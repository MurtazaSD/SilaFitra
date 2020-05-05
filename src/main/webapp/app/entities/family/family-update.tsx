import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './family.reducer';
import { IFamily } from 'app/shared/model/family.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFamilyUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FamilyUpdate = (props: IFamilyUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { familyEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/family');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...familyEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="silaFitraApp.family.home.createOrEditLabel">
            <Translate contentKey="silaFitraApp.family.home.createOrEditLabel">Create or edit a Family</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : familyEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="family-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="family-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="hofEjamaatIdLabel" for="family-hofEjamaatId">
                  <Translate contentKey="silaFitraApp.family.hofEjamaatId">Hof Ejamaat Id</Translate>
                </Label>
                <AvField
                  id="family-hofEjamaatId"
                  type="string"
                  className="form-control"
                  name="hofEjamaatId"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="hofFullNameLabel" for="family-hofFullName">
                  <Translate contentKey="silaFitraApp.family.hofFullName">Hof Full Name</Translate>
                </Label>
                <AvField id="family-hofFullName" type="text" name="hofFullName" />
              </AvGroup>
              <AvGroup>
                <Label id="emailAddressLabel" for="family-emailAddress">
                  <Translate contentKey="silaFitraApp.family.emailAddress">Email Address</Translate>
                </Label>
                <AvField id="family-emailAddress" type="text" name="emailAddress" />
              </AvGroup>
              <AvGroup>
                <Label id="phoneNumberLabel" for="family-phoneNumber">
                  <Translate contentKey="silaFitraApp.family.phoneNumber">Phone Number</Translate>
                </Label>
                <AvField id="family-phoneNumber" type="text" name="phoneNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="totalGentsLabel" for="family-totalGents">
                  <Translate contentKey="silaFitraApp.family.totalGents">Total Gents</Translate>
                </Label>
                <AvField id="family-totalGents" type="string" className="form-control" name="totalGents" />
              </AvGroup>
              <AvGroup>
                <Label id="totalLadiesLabel" for="family-totalLadies">
                  <Translate contentKey="silaFitraApp.family.totalLadies">Total Ladies</Translate>
                </Label>
                <AvField id="family-totalLadies" type="string" className="form-control" name="totalLadies" />
              </AvGroup>
              <AvGroup>
                <Label id="totalKidsLabel" for="family-totalKids">
                  <Translate contentKey="silaFitraApp.family.totalKids">Total Kids</Translate>
                </Label>
                <AvField id="family-totalKids" type="string" className="form-control" name="totalKids" />
              </AvGroup>
              <AvGroup>
                <Label id="totalPregnantsLabel" for="family-totalPregnants">
                  <Translate contentKey="silaFitraApp.family.totalPregnants">Total Pregnants</Translate>
                </Label>
                <AvField id="family-totalPregnants" type="string" className="form-control" name="totalPregnants" />
              </AvGroup>
              <AvGroup>
                <Label id="totalDeceseadLabel" for="family-totalDecesead">
                  <Translate contentKey="silaFitraApp.family.totalDecesead">Total Decesead</Translate>
                </Label>
                <AvField id="family-totalDecesead" type="string" className="form-control" name="totalDecesead" />
              </AvGroup>
              <AvGroup>
                <Label id="zakatulFitrLabel" for="family-zakatulFitr">
                  <Translate contentKey="silaFitraApp.family.zakatulFitr">Zakatul Fitr</Translate>
                </Label>
                <AvField id="family-zakatulFitr" type="string" className="form-control" name="zakatulFitr" />
              </AvGroup>
              <AvGroup>
                <Label id="najwatusukrLabel" for="family-najwatusukr">
                  <Translate contentKey="silaFitraApp.family.najwatusukr">Najwatusukr</Translate>
                </Label>
                <AvField id="family-najwatusukr" type="string" className="form-control" name="najwatusukr" />
              </AvGroup>
              <AvGroup>
                <Label id="khumusLabel" for="family-khumus">
                  <Translate contentKey="silaFitraApp.family.khumus">Khumus</Translate>
                </Label>
                <AvField id="family-khumus" type="string" className="form-control" name="khumus" />
              </AvGroup>
              <AvGroup>
                <Label id="silatulImamLabel" for="family-silatulImam">
                  <Translate contentKey="silaFitraApp.family.silatulImam">Silatul Imam</Translate>
                </Label>
                <AvField id="family-silatulImam" type="string" className="form-control" name="silatulImam" />
              </AvGroup>
              <AvGroup>
                <Label id="nazrulMaqamLabel" for="family-nazrulMaqam">
                  <Translate contentKey="silaFitraApp.family.nazrulMaqam">Nazrul Maqam</Translate>
                </Label>
                <AvField id="family-nazrulMaqam" type="string" className="form-control" name="nazrulMaqam" />
              </AvGroup>
              <AvGroup>
                <Label id="lastUpdateTstampLabel" for="family-lastUpdateTstamp">
                  <Translate contentKey="silaFitraApp.family.lastUpdateTstamp">Last Update Tstamp</Translate>
                </Label>
                <AvField id="family-lastUpdateTstamp" type="date" className="form-control" name="lastUpdateTstamp" />
              </AvGroup>
              <AvGroup>
                <Label id="submittedByLabel" for="family-submittedBy">
                  <Translate contentKey="silaFitraApp.family.submittedBy">Submitted By</Translate>
                </Label>
                <AvField id="family-submittedBy" type="text" name="submittedBy" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/family" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  familyEntity: storeState.family.entity,
  loading: storeState.family.loading,
  updating: storeState.family.updating,
  updateSuccess: storeState.family.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FamilyUpdate);
