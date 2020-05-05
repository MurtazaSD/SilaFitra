import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './family.reducer';
import { IFamily } from 'app/shared/model/family.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFamilyProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Family = (props: IFamilyProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { familyList, match, loading } = props;
  return (
    <div>
      <h2 id="family-heading">
        <Translate contentKey="silaFitraApp.family.home.title">Families</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="silaFitraApp.family.home.createLabel">Create new Family</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {familyList && familyList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.hofEjamaatId">Hof Ejamaat Id</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.hofFullName">Hof Full Name</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.emailAddress">Email Address</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.totalGents">Total Gents</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.totalLadies">Total Ladies</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.totalKids">Total Kids</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.totalPregnants">Total Pregnants</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.totalDecesead">Total Decesead</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.zakatulFitr">Zakatul Fitr</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.najwatusukr">Najwatusukr</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.khumus">Khumus</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.silatulImam">Silatul Imam</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.nazrulMaqam">Nazrul Maqam</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.lastUpdateTstamp">Last Update Tstamp</Translate>
                </th>
                <th>
                  <Translate contentKey="silaFitraApp.family.submittedBy">Submitted By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {familyList.map((family, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${family.id}`} color="link" size="sm">
                      {family.id}
                    </Button>
                  </td>
                  <td>{family.hofEjamaatId}</td>
                  <td>{family.hofFullName}</td>
                  <td>{family.emailAddress}</td>
                  <td>{family.phoneNumber}</td>
                  <td>{family.totalGents}</td>
                  <td>{family.totalLadies}</td>
                  <td>{family.totalKids}</td>
                  <td>{family.totalPregnants}</td>
                  <td>{family.totalDecesead}</td>
                  <td>{family.zakatulFitr}</td>
                  <td>{family.najwatusukr}</td>
                  <td>{family.khumus}</td>
                  <td>{family.silatulImam}</td>
                  <td>{family.nazrulMaqam}</td>
                  <td>
                    <TextFormat type="date" value={family.lastUpdateTstamp} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{family.submittedBy}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${family.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${family.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${family.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="silaFitraApp.family.home.notFound">No Families found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ family }: IRootState) => ({
  familyList: family.entities,
  loading: family.loading
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Family);
