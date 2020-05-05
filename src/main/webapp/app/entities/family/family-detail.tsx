import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './family.reducer';
import { IFamily } from 'app/shared/model/family.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFamilyDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FamilyDetail = (props: IFamilyDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { familyEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="silaFitraApp.family.detail.title">Family</Translate> [<b>{familyEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="hofEjamaatId">
              <Translate contentKey="silaFitraApp.family.hofEjamaatId">Hof Ejamaat Id</Translate>
            </span>
          </dt>
          <dd>{familyEntity.hofEjamaatId}</dd>
          <dt>
            <span id="hofFullName">
              <Translate contentKey="silaFitraApp.family.hofFullName">Hof Full Name</Translate>
            </span>
          </dt>
          <dd>{familyEntity.hofFullName}</dd>
          <dt>
            <span id="emailAddress">
              <Translate contentKey="silaFitraApp.family.emailAddress">Email Address</Translate>
            </span>
          </dt>
          <dd>{familyEntity.emailAddress}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="silaFitraApp.family.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{familyEntity.phoneNumber}</dd>
          <dt>
            <span id="totalGents">
              <Translate contentKey="silaFitraApp.family.totalGents">Total Gents</Translate>
            </span>
          </dt>
          <dd>{familyEntity.totalGents}</dd>
          <dt>
            <span id="totalLadies">
              <Translate contentKey="silaFitraApp.family.totalLadies">Total Ladies</Translate>
            </span>
          </dt>
          <dd>{familyEntity.totalLadies}</dd>
          <dt>
            <span id="totalKids">
              <Translate contentKey="silaFitraApp.family.totalKids">Total Kids</Translate>
            </span>
          </dt>
          <dd>{familyEntity.totalKids}</dd>
          <dt>
            <span id="totalPregnants">
              <Translate contentKey="silaFitraApp.family.totalPregnants">Total Pregnants</Translate>
            </span>
          </dt>
          <dd>{familyEntity.totalPregnants}</dd>
          <dt>
            <span id="totalDecesead">
              <Translate contentKey="silaFitraApp.family.totalDecesead">Total Decesead</Translate>
            </span>
          </dt>
          <dd>{familyEntity.totalDecesead}</dd>
          <dt>
            <span id="zakatulFitr">
              <Translate contentKey="silaFitraApp.family.zakatulFitr">Zakatul Fitr</Translate>
            </span>
          </dt>
          <dd>{familyEntity.zakatulFitr}</dd>
          <dt>
            <span id="najwatusukr">
              <Translate contentKey="silaFitraApp.family.najwatusukr">Najwatusukr</Translate>
            </span>
          </dt>
          <dd>{familyEntity.najwatusukr}</dd>
          <dt>
            <span id="khumus">
              <Translate contentKey="silaFitraApp.family.khumus">Khumus</Translate>
            </span>
          </dt>
          <dd>{familyEntity.khumus}</dd>
          <dt>
            <span id="silatulImam">
              <Translate contentKey="silaFitraApp.family.silatulImam">Silatul Imam</Translate>
            </span>
          </dt>
          <dd>{familyEntity.silatulImam}</dd>
          <dt>
            <span id="nazrulMaqam">
              <Translate contentKey="silaFitraApp.family.nazrulMaqam">Nazrul Maqam</Translate>
            </span>
          </dt>
          <dd>{familyEntity.nazrulMaqam}</dd>
          <dt>
            <span id="lastUpdateTstamp">
              <Translate contentKey="silaFitraApp.family.lastUpdateTstamp">Last Update Tstamp</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={familyEntity.lastUpdateTstamp} type="date" format={APP_LOCAL_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="submittedBy">
              <Translate contentKey="silaFitraApp.family.submittedBy">Submitted By</Translate>
            </span>
          </dt>
          <dd>{familyEntity.submittedBy}</dd>
        </dl>
        <Button tag={Link} to="/family" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/family/${familyEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ family }: IRootState) => ({
  familyEntity: family.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FamilyDetail);
