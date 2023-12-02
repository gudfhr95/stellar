import * as pulumi from '@pulumi/pulumi';
import * as awsx from '@pulumi/awsx';

const config = new pulumi.Config();

export const stellarRepository = new awsx.ecr.Repository('stellar', {
  name: 'stellar',
});
